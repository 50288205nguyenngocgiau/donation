//package com.poly.dax.controller;
//
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//
//import com.paypal.base.rest.PayPalRESTException;
//import com.poly.dax.common.entity.OrderDetail;
//import com.poly.dax.service.PaymentSevice;
//
//@Controller
//public class AuthorizePaymentController {
//	@GetMapping("/check_out")
//	public String getAuthorizePayment() {
//		return "checkout";
//	}
//	@PostMapping("/authorize_payment")
//	public String checkout(@RequestParam("product") String product,
//			@RequestParam("subtotal") String subtotal,
//			@RequestParam("shipping") String shipping,
//			@RequestParam("tax") String tax,
//			@RequestParam("total") String total) throws PayPalRESTException{
//		OrderDetail orderDetail = new OrderDetail(product,subtotal,shipping,tax,total);
//		
//			PaymentSevice paymentSevice = new PaymentSevice();
//			String approvalLink = paymentSevice.authorizePayment(orderDetail);
//			
//			return "redirect:" + approvalLink;
//	}
//}
