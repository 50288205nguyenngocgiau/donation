//package com.poly.dax.service;
//
//import java.util.ArrayList;
//import java.util.List;
//
//import org.springframework.stereotype.Service;
//
//import com.paypal.api.payments.Amount;
//import com.paypal.api.payments.Details;
//import com.paypal.api.payments.Item;
//import com.paypal.api.payments.ItemList;
//import com.paypal.api.payments.Links;
//import com.paypal.api.payments.Payer;
//import com.paypal.api.payments.PayerInfo;
//import com.paypal.api.payments.Payment;
//import com.paypal.api.payments.RedirectUrls;
//import com.paypal.api.payments.Transaction;
//import com.paypal.base.rest.APIContext;
//import com.paypal.base.rest.PayPalRESTException;
//import com.poly.dax.common.entity.OrderDetail;
//
//@Service
//public class PaymentSevice {
//	private static final String CLIENT_ID = "AXYKAY88g8sU4YBKBmZGXz9bwnV0xNSvMdpNP1j3QiCnScJ4HMJEYN78DrNskUZkBQixHfRrr6PW6R0f";
//	private static final String CLIEN_SECRET = "EBf-zb3Hp7-pQQIgLq11RJJ9kHa9vOBTckRnx-P0hHDJYJL4jqA7cqKuWXwM5ZmSgglucBgOeEGQczYq";
//	private static final String MODE = "sandbox";
//	
//	public String authorizePayment(OrderDetail orderDetail) throws PayPalRESTException {
//		Payer payer = getPayerInfomation();
//		
//		RedirectUrls redirectURLs = getRedirectURLs();
//		List<Transaction> listTransaction = getTransactionInformation(orderDetail);
//		
//		Payment requestPayment = new Payment();
//		requestPayment.setTransactions(listTransaction)
//		.setRedirectUrls(redirectURLs)
//		.setIntent("authorize");
//		
//		APIContext apiContext = new APIContext(CLIENT_ID,CLIEN_SECRET,MODE);
//		Payment approvedPayment = requestPayment.create(apiContext);
//		System.out.println(approvedPayment);
//		
//		return null;
//	}
//	private String getApprovalLink(Payment approvedPayment) {
//		List<Links> links = approvedPayment.getLinks();
//		String approvalLink = null;
//		for(Links link: links) {
//			if(link.getRel().equalsIgnoreCase("approval_url")) {
//				approvalLink = link.getHref();
//			}
//		}
//		return approvalLink;
//	}
//
//	private RedirectUrls getRedirectURLs() {
//		RedirectUrls redirectUrls = new RedirectUrls();
//		redirectUrls.setCancelUrl("http://localhost/home/cancel");
//		redirectUrls.setReturnUrl("http://localhost/review");
//		
//		return redirectUrls;
//	}
//
//	private Payer getPayerInfomation() {
//		Payer payer = new Payer();
//		payer.setPaymentMethod("paypal");
//		
//		PayerInfo payerInfo = new PayerInfo();
//		payerInfo.setFirstName("giau");
//		payerInfo.setLastName("nguyen");
//		payerInfo.setEmail("giau7863@gmail.com");
//		
//		payer.setPayerInfo(payerInfo);
//		return payer;
//	}
//	private List<Transaction> getTransactionInformation(OrderDetail orderDetail){
//		Details detail = new Details();
//		
//		detail.setShipping(orderDetail.getShipping());
//		detail.setSubtotal(orderDetail.getSubtotal());
//		detail.setTax(orderDetail.getTax());
//		
//		Amount amount = new Amount();
//		amount.setCurrency("USD");
//		amount.setTotal(orderDetail.getTotal());
//		amount.setDetails(detail);
//		
//		Transaction transaction = new Transaction();
//		transaction.setAmount(amount);
//		transaction.setDescription(orderDetail.getProductName());
//		
//		ItemList itemList = new ItemList();
//		List<Item> items = new ArrayList<>();
//		
//		Item item = new Item();
//		item.setCurrency("USD")
//		.setName(orderDetail.getProductName())
//		.setPrice(orderDetail.getSubtotal())
//		.setTax(orderDetail.getTax())
//		.setQuantity("1");
//		
//		items.add(item);
//		itemList.setItems(items);
//		transaction.setItemList(itemList);
//		
//		List<Transaction> listTransaction = new ArrayList<>();
//		listTransaction.add(transaction);
//		
//		return listTransaction;
//	}
//}
