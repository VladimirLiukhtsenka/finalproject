package com.liukhtenko.ticket.validator;

        import com.liukhtenko.ticket.exception.ServiceException;
        import org.testng.Assert;
        import org.testng.annotations.Test;

public class EncryptionPasswordTest {

    @Test
    public void testEncrypt() throws ServiceException {
        String actual = "TestPassword123";
        String expected = EncryptionPassword.encrypt(actual);
        Assert.assertNotEquals(actual, expected);
    }
}