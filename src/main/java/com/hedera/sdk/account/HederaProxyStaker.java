package com.hedera.sdk.account;

import java.io.Serializable;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.hederahashgraph.api.proto.java.AccountID;
import com.hederahashgraph.api.proto.java.ProxyStaker;
/**
 * account proxy staking to a given account, and the amount proxy staked
 *
 */
public class HederaProxyStaker implements Serializable {
	final static Logger logger = LoggerFactory.getLogger(HederaProxyStaker.class);
	private static final long serialVersionUID = 1;

	/**
	 * the shard number (nonnegative)
	 */
	public long shardNum = 0;

	/**
	 * the realm number (nonnegative)
	 */
	public long realmNum = -1;

	/**
	 * a nonnegative number unique within its realm
	 */
	public long accountNum = 1;
	/**
	 * the amount being staked
	 */
	public long amount = 0;
	/**
	 * Default constructor
	 */
	public HederaProxyStaker() {
	   	logger.trace("Start - Object init");
	   	logger.trace("End - Object init");
	}
	/**
	 * Constructor from a shard, real, account and amount
	 * @param shardNum the shard number for the proxy staker
	 * @param realmNum the realm number for the proxy staker
	 * @param accountNum the account number for the proxy staker
	 * @param amount the amount to proxy
	 */
	public HederaProxyStaker(long shardNum, long realmNum, long accountNum, long amount) {
	   	logger.trace("Start - Object init in shard {}, realm {}. Account number {}, amount {}", shardNum, realmNum, accountNum, amount);
 		this.shardNum = shardNum;
		this.realmNum = realmNum;
		this.accountNum = accountNum;
		this.amount = amount;
		
	   	logger.trace("End - Object init");
	}

	/**
	 * Construct from a {@link ProxyStaker} protobuf stream
	 * @param proxyStaker a proxy staker protobuf
	 */
	public HederaProxyStaker(ProxyStaker proxyStaker) {
	   	logger.trace("Start - Object init in proxyStaker {}", proxyStaker);
		this.shardNum = proxyStaker.getAccountID().getShardNum();
		this.realmNum = proxyStaker.getAccountID().getRealmNum();
		this.accountNum = proxyStaker.getAccountID().getAccountNum();
		this.amount = proxyStaker.getAmount();
	   	logger.trace("End - Object init");
	}

	/**
	 * Generate a {@link ProxyStaker} protobuf stream for this object
	 * @return {@link ProxyStaker} 
	 */
	public ProxyStaker getProtobuf() {
	   	logger.trace("Start - getProtobuf");
		
	   	ProxyStaker.Builder accountAmount = ProxyStaker.newBuilder();
		AccountID.Builder accountID = AccountID.newBuilder();
		
		accountID.setAccountNum(this.accountNum);
		accountID.setRealmNum(this.realmNum);
		accountID.setShardNum(this.shardNum);
		
		accountAmount.setAccountID(accountID);
		accountAmount.setAmount(this.amount);
	   	
	   	logger.trace("End - getProtobuf");

		return accountAmount.build();
	}
}