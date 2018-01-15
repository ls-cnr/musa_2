package org.icar.musa.core;

import java.util.ArrayList;
import java.util.HashMap;

import org.icar.specification.linear_temporal_logic.net.PNStateEnum;

public interface TokenConfInterface {

	/**
	 * Adds a token in a net.
	 *
	 * @param net
	 *            the net
	 * @param token
	 *            the token
	 */
	void addToken(String net, String token);

	/**
	 * Removes a token from a net.
	 *
	 * @param net
	 *            the net
	 * @param token
	 *            the token
	 */
	void removeToken(String net, String token);

	/**
	 * Gets the net state.
	 *
	 * @param net
	 *            the net
	 * @return the net state
	 */
	PNStateEnum getNetState(String net);

	/**
	 * Sets the net state.
	 *
	 * @param net
	 *            the net
	 * @param state
	 *            the state
	 */
	void setNetState(String net, PNStateEnum state);

	/**
	 * Removes the net state from the dictionary.
	 *
	 * @param net
	 *            the net
	 */
	void removeNetState(String net);

	/**
	 * Gets the nets state.
	 *
	 * @return the nets state
	 */
	HashMap<String, PNStateEnum> getNetsState();

	/**
	 * Gets the tokens.
	 *
	 * @param net
	 *            the net
	 * @return the tokens
	 */
	ArrayList<String> getTokens(String net);

	/**
	 * Gets the conf.
	 *
	 * @return the conf
	 */
	HashMap<String, ArrayList<String>> getConf();

	/**
	 * Gets the nets.
	 *
	 * @return the nets
	 */
	Iterable<String> getNets();

}