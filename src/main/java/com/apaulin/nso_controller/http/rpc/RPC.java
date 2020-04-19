/**
 * 
 */
package com.apaulin.nso_controller.http.rpc;

import java.io.UnsupportedEncodingException;

import org.apache.http.entity.StringEntity;

/**
 * @author Anthony Paulin
 * @version 0.1
 * @category RPC
 * @since 06-01-2020
 *
 */
public interface RPC {
	StringEntity getRequestEntity() throws UnsupportedEncodingException;
}
