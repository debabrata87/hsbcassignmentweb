package com.example.hsbcassignmentweb.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.hsbcassignmentweb.model.Balance;
import com.example.hsbcassignmentweb.model.Payment;
import com.example.hsbcassignmentweb.repository.BalanceRepo;

@Controller
public class PaymentService {

	@Autowired
	BalanceRepo repo;
	boolean t = false;

	@RequestMapping("home")
	public String home(Model model) {
		model.addAttribute("tile", "Payment System ");
		Payment pm = new Payment();
		model.addAttribute("payment", pm);

		ArrayList<Balance> bList = new ArrayList<Balance>();
		repo.findAll().forEach((e) -> {
			if (e.getBalance() > 0) {
				bList.add(e);

			}
		});

		System.out.println(" \n Balance List  : " + (bList == null ? " No Balance  " : bList));

		model.addAttribute("balance", bList);

		return "home";
	}

	@PostMapping("balance")
	public String processPayment(@ModelAttribute("payment") Payment pm, Model model, HttpSession session,
			RedirectAttributes redirAttrs) {

		
		
		
		if(!validatePayment( pm.getCurrency() ,pm.getAmount(), model, session,
				 redirAttrs)) {
			return "redirect:/home/";
			//return "home";
		}
		
		

		
		
		List<Balance> list = repo.findByCurrency(pm.getCurrency().trim());

		if (list != null && !list.isEmpty()) {
			Balance b = list.get(0);
			b.setBalance(b.getBalance() - Double.parseDouble(pm.getAmount()));
			repo.save(b);

		}
		else {
			
			ArrayList<Balance> cList = new ArrayList<Balance>();
			repo.findAll().forEach((e) -> {
				if (e.getBalance() > 0) {
					cList.add(e);

				}
			});
			
			model.addAttribute("balance", cList);

			session.setAttribute("message", "Payment Currency is not loead on System");
			
			redirAttrs.addFlashAttribute("perror", "Payment Currency is not loead on System "+(pm.getCurrency() == null ? " No Input " : pm.getCurrency() )
			+ " Amount : "+pm.getAmount() );
	        return "redirect:/home/";
			
			
			
		}
				
		ArrayList<Balance> bList = new ArrayList<Balance>();
		repo.findAll().forEach((e) -> {
			if (e.getBalance() > 0) {
				bList.add(e);

			}
		});
		
		
		
		System.out.println(" \n After Processing Balance List  : " + (bList == null ? " No Balance  " : bList));

		model.addAttribute("balance", bList);

		session.setAttribute("message", "Payment Processed");

		redirAttrs.addFlashAttribute("psuccess", "Everything went just fine.");
		
		
		//return "redirect:/home/";
		return "home";
	}

	public boolean isNumeric(String str) {
		try {
			Double.parseDouble(str);
			System.out.println(" \n Amount " + str);

			return true;
		} catch (NumberFormatException e) {
			System.out.println(" \n Amount non numeric " + str);

			return false;
		}
	}
	
	public boolean validatePayment( String currency ,String amount,Model model,HttpSession session,
			RedirectAttributes redirAttrs) {
		boolean isValid=true;
		if (( currency == null || currency.trim().equals(""))
				|| ( amount == null || amount.trim().equals("") || !isNumeric( amount  )  )) {

			System.out.println(" \n Invalid Input  : " + ( currency == null ? " No Input " : currency )
					+ " Payment Amount : " + amount );
			System.out.println(" \n --------------Try Again ------------------------");

			
			ArrayList<Balance> cList = new ArrayList<Balance>();
			repo.findAll().forEach((e) -> {
				if (e.getBalance() > 0) {
					cList.add(e);

				}
			});

			System.out.println(" \n Balance List  : " + (cList == null ? " No Balance  " : cList));

			model.addAttribute("balance", cList);
			
			session.setAttribute("message", "Payment invalid ");
			
			redirAttrs.addFlashAttribute("perror", "Payment invalid ,Currency : " +(currency == null ? " No Input " : currency )+ " Amount : " + amount);
	        return false;
			
			//return "home";
		}

		return isValid;
		
	}

}
