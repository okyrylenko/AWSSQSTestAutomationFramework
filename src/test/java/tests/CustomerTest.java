package tests;

import core.helpers.HttpResponseDecorator;
import core.helpers.Ioc;
import core.helpers.LocalDataRepository;
import core.interfaces.ICustomerServiceHttp;
import core.interfaces.ICustomerServiceSqs;
import core.interfaces.IHttpPurchaseRepository;
import core.models.CustomerToSend;
import core.models.Lead;
import core.models.CustomerToReceive;
import org.json.simple.parser.ParseException;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.Guice;
import org.testng.annotations.Test;
import dataaccess.api.HttpCustomerRepository;
import dataaccess.api.HttpPurchaseRepository;
import dataaccess.sqs.SqsCustomerRepository;
import testlogic.services.CustomerServiceHttp;
import testlogic.services.CustomerServiceSqs;

import javax.inject.Inject;
import java.io.IOException;
import java.net.URISyntaxException;

@Guice(modules = Ioc.class)
public class CustomerTest {

    @Inject
    private ICustomerServiceHttp customerServiceApi;
    @Inject
    private ICustomerServiceSqs customerServiceSqs;
    @Inject
    private IHttpPurchaseRepository purchase;
    @Inject
    private HttpResponseDecorator customerResponse;
    @Inject
    private HttpResponseDecorator lead;

    @Test()
    public void ShouldPostMessageInSQSWhenNewCustomerCreated() throws IOException, ParseException, URISyntaxException, InterruptedException {
        //Given I have customer in sqs
        CustomerToReceive customer = customerResponse.setResponse(customerServiceApi.add(LocalDataRepository.getCustomer()))
                .getBody(CustomerToReceive.class);
        //When I retrieve a customer from sqs
        CustomerToReceive customerFromSQS = this.customerServiceSqs.find(customer.getId());

        //Then id should not be null;first, last name, and email should match with what was sent
        Assert.assertEquals(customer.getEmail(), customerFromSQS.getEmail());
        Assert.assertEquals(customer.getFName(), customerFromSQS.getFName());
        Assert.assertEquals(customer.getLName(), customerFromSQS.getLName());
        Assert.assertNotNull(customer.getId());
    }

    @Test()
    public void ShouldConvertNameToUpperCaseWhenNewCustomerCreated() throws IOException, ParseException, URISyntaxException, InterruptedException {
        //Given I have customer in sqs
        CustomerToSend customerSent = LocalDataRepository.getCustomer();
        CustomerToReceive customerReceived = customerResponse.setResponse(this.customerServiceApi.add(customerSent))
                .getBody(CustomerToReceive.class);
        CustomerToReceive customerFromSQS = this.customerServiceSqs.find(customerReceived.getId());
        //Then id should not be null;first, last name, and email should match with what was sent
        Assert.assertEquals(customerFromSQS.getFName().toCharArray()[0], customerSent.getFName().toUpperCase().toCharArray()[0]);
        Assert.assertEquals(customerFromSQS.getLName().toCharArray()[0], customerSent.getLName().toUpperCase().toCharArray()[0]);
    }

    @Test()
    public void ShouldLinkPurchaseHistoryToNewEmail() throws IOException, URISyntaxException, InterruptedException, ParseException {
        purchase.createNewPurchase(LocalDataRepository.getPurchase());
        CustomerToReceive customer = customerResponse.setResponse(this.customerServiceApi.add(LocalDataRepository.getCustomer())).getBody(CustomerToReceive.class);
        //When I retrieve a customer from sqs
        Lead lead = this.lead.setResponse(this.customerServiceApi.getCustomer(customer.getId())).getBody(Lead.class);
        Assert.assertNotNull(lead.getPurchaseHistory());
    }
}
