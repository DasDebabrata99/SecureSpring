package com.example.SecuritySection1.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BankController {

    @GetMapping("/myAccountDetails")
	public String getAccount() {
		return "Here are my account details";
	}
    
    @GetMapping("/myBalanceDetails")
	public String getBalance() {
		return "Here are my balance details";
	}
    
    @GetMapping("/myLoan")
	public String getLoan() {
		return "Here are my loan details";
	}
    
    @GetMapping("/contact")
	public String getContact() {
		return "Here are my contact	details";
	}
    
    @GetMapping("/notice")
	public String getNotice() {
		return "Here are my notice details";
	}
}
