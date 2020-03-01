package com.liukhtenko.ticket.validator;

import com.liukhtenko.ticket.command.FormParameterName;
import com.liukhtenko.ticket.exception.ServiceException;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * The class that encodes password.
 *
 * @author Vladimir Liukhtenko
 * @version 1.25 02 Feb 2020
 */
public class EncryptionPassword {
    public static String encrypt(String value) throws ServiceException {
        String generatedPassword;
        try {
            MessageDigest md = MessageDigest.getInstance(FormParameterName.MESSAGE_DIGEST);
            md.update(value.getBytes());
            byte[] bytes = md.digest();
            StringBuilder sb = new StringBuilder();
            for (byte aByte : bytes) {
                sb.append(Integer.toString((aByte & FormParameterName.HEXADECIMAL_FORMAT)
                        + FormParameterName.HEXADECIMAL_FORMAT_ADD, FormParameterName.RADIX)
                        .substring(FormParameterName.OFFSET));
            }
            generatedPassword = sb.toString();
        } catch (NoSuchAlgorithmException e) {
            throw new ServiceException(e);
        }
        return generatedPassword;
    }
}
