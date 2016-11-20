package com.whoknows.mail;

import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.dm.model.v20151123.SingleSendMailRequest;
import com.aliyuncs.dm.model.v20151123.SingleSendMailResponse;
import com.aliyuncs.profile.DefaultProfile;
import com.aliyuncs.profile.IClientProfile;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class AliMailService {
	
	private final Logger log = LoggerFactory.getLogger(this.getClass());
	
	@Value("${mail.ali.key}")
	private String key;
	@Value("${mail.ali.secret}")
	private String secret;
	@Value("${mail.address}")
	private String fromAddress;
	@Value("${mail.name}")
	private String fromName;
	
	public boolean regester(RegisterMailInfo registerMailInfo) {
		
		if (StringUtils.isEmpty(key)
				|| StringUtils.isEmpty(secret)
				|| StringUtils.isEmpty(fromAddress)
				|| StringUtils.isEmpty(fromName)) {
			log.error("Ali mail not set");
			return false;
		}
		if (!registerMailInfo.validate()) {
			log.error("registerMailInfo not set");
			return false;
		}
		
		IClientProfile profile = DefaultProfile.getProfile("cn-hangzhou", key, secret);
		IAcsClient client = new DefaultAcsClient(profile);
		SingleSendMailRequest request = new SingleSendMailRequest();
		try {
			request.setAccountName(fromAddress);
			request.setFromAlias(fromName);
			request.setAddressType(1);
//			request.setTagName("控制台创建的标签");
			request.setReplyToAddress(true);
			request.setToAddress(registerMailInfo.getToAddress());
			request.setSubject(registerMailInfo.getTitle());
			request.setHtmlBody(registerMailInfo.getContent());
			SingleSendMailResponse httpResponse = client.getAcsResponse(request);
			httpResponse.getRequestId();
			log.info("send register email to {}", registerMailInfo.getToAddress());
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
}
