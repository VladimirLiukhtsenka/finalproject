package com.liukhtenko.ticket.validator;

import com.liukhtenko.ticket.exception.ServiceException;
import org.testng.Assert;
import org.testng.annotations.Test;
/**
 * The class that testing
 * @see EncryptionPassword
 *
 * @author Vladimir Liukhtenko
 * @version 1.25 02 Feb 2020
 */
public class EncryptionPasswordTest {

    @Test
    public void testEncrypt() throws ServiceException {
        String actual = "TestPassword123";
        String expected = EncryptionPassword.encrypt(actual);
        Assert.assertNotEquals(actual, expected);
    }
}