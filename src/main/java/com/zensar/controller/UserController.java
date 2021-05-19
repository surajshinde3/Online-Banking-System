package com.zensar.controller;

import java.security.Principal;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.zensar.model.SalaryAccount;
import com.zensar.model.SalaryTransaction;
import com.zensar.model.SavingsAccount;
import com.zensar.model.SavingsTransaction;
import com.zensar.model.User;
import com.zensar.services.AccountService;
import com.zensar.services.TransactionService;
import com.zensar.services.UserService;

@Controller
public class UserController {

	@Autowired
	UserService userService;

	@Autowired
	private AccountService aservice;

	@Autowired

	private TransactionService tservice;

	@GetMapping("/getregister")
	public String getRegisterPage() {
		return "register";
	}

	@GetMapping("/login")
	public String getLoginPage() {
		return "login";
	}

	@GetMapping("/home")
	public String getHomePage() {
		return "home";
	}

	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public String registerUserAccount(@Valid @ModelAttribute User user, Model model) {
		System.out.println(user.getEmail());
		System.out.println(user.getUsername());

		if (userService.checkUserExists(user.getUsername(), user.getEmail())) {

			if (userService.checkEmailExists(user.getEmail())) {
				model.addAttribute("emailExists", true);
			}

			if (userService.checkUsernameExists(user.getUsername())) {
				model.addAttribute("usernameExists", true);
			}

			return "register";
		} else {

			userService.save(user);
			return "redirect:/login";
		}
	}

	@RequestMapping(value = "/deposit", method = RequestMethod.POST)
	public String deposit(@RequestParam String accountType, @RequestParam String amount, Principal principal,
			Model model) {
		System.out.println(accountType);
		aservice.deposit(accountType, Double.parseDouble(amount), principal);
		String s = amount;

		s = s.concat(" Rupees Successfully Deposited into " + accountType + " Account!!");
		model.addAttribute("amount", s);
		return "home";
	}

	@RequestMapping(value = "/withdrawl", method = RequestMethod.POST)
	public String withdrawl(@RequestParam String accountType, @RequestParam String amount, Principal principal,
			Model model) {
		System.out.println(accountType);
		aservice.withdraw(accountType, Double.parseDouble(amount), principal);

		String s = amount;
		s = s.concat(" Rupees Withdrawl Successfull from " + accountType + " Account!!");
		model.addAttribute("amount1", s);

		return "withdraw";
	}

	@GetMapping("/withdrawpage")
	public String getWithdrawPage() {
		return "withdraw";
	}

	@GetMapping("/accountDetails")
	public String getAccountDetails(Model model, Principal principal) {

		User user = userService.findByUsername(principal.getName());
		SavingsAccount savingsAccount = user.getSavingsAccount();
		SalaryAccount salaryAccount = user.getSalaryAccount();

		model.addAttribute("savingsAccount", savingsAccount);
		model.addAttribute("salaryAccount", salaryAccount);
		return "accountDetails";
	}

	@RequestMapping(value = "/getTransactions", method = RequestMethod.POST)
	public String savingsAccount(@RequestParam String accountType, Model model, Principal principal) {

		if (accountType.equalsIgnoreCase("Savings")) {
			List<SavingsTransaction> savingsTransactionList = tservice.findSavingsTransactionList(principal.getName());

			User user = userService.findByUsername(principal.getName());
			SavingsAccount savingsAccount = user.getSavingsAccount();

			model.addAttribute("savingsAccount", savingsAccount);
			model.addAttribute("savingsTransactionList", savingsTransactionList);
		} else if (accountType.equalsIgnoreCase("Salary")) {
			List<SalaryTransaction> salaryTransactionList = tservice.findSalaryTransactionList(principal.getName());

			User user = userService.findByUsername(principal.getName());
			SalaryAccount salaryAccount = user.getSalaryAccount();

			model.addAttribute("salaryAccount", salaryAccount);
			model.addAttribute("salaryTransactionList", salaryTransactionList);
		}

		return "transactions";
	}

	@GetMapping("/getTransactionPage")
	public String getTransactionPage() {
		return "transactions";
	}
}
