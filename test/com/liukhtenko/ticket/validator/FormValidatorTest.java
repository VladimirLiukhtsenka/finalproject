package com.liukhtenko.ticket.validator;

import com.liukhtenko.ticket.exception.ServiceException;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
/**
 * The class that testing
 * {@link  com.liukhtenko.ticket.validator.FormValidator}
 *
 * @author Vladimir Liukhtenko
 * @version 1.25 02 Feb 2020
 */
public class FormValidatorTest {

    @DataProvider(name = "provideString")
    public Object[] provideString() {
        return new String[][]{
                {"test@gmail.com", FormRegexValidator.EMAIL},
                {"test123password", FormRegexValidator.PASSWORD}
        };
    }

    @Test(dataProvider = "provideString")
    public void testIsValidString(String value, String pattern) {
        Assert.assertTrue(FormValidator.isValidString(value, pattern));
    }

    @DataProvider(name = "provideNumber")
    public Object[] provideNumber() {
        return new String[][]{
                {"123"},
                {"11"}
        };
    }

    @Test(dataProvider = "provideNumber")
    public void testIsValidNumber(String value) throws ServiceException {
        Assert.assertTrue(FormValidator.isValidNumber(value));
    }

    @DataProvider(name = "providePrice")
    public Object[][] providePrice() {
        return new String[][]{
                {"15"},
                {"11.0"}
        };
    }

    @Test(dataProvider = "providePrice")
    public void testIsValidPrice(String value) throws ServiceException {
        Assert.assertTrue(FormValidator.isValidPrice(value));
    }

    @DataProvider(name = "provideDate")
    public Object[] provideDate() {
        return new String[][]{
                {"2020-02-12 19:00:00"},
                {"2020-04-17 13:00:01"}
        };
    }

    @Test(dataProvider = "provideDate")
    public void testIsValidDate(String value) throws ServiceException {
        Assert.assertTrue(FormValidator.isValidDate(value));
    }
}