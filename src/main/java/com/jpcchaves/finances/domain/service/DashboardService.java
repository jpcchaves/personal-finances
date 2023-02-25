package com.jpcchaves.finances.domain.service;

import com.jpcchaves.finances.domain.Enum.ETitleType;
import com.jpcchaves.finances.domain.model.User;
import com.jpcchaves.finances.dto.dashboard.DashboardResponseDto;
import com.jpcchaves.finances.dto.title.TitleResponseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class DashboardService {
    @Autowired
    private TitleService titleService;

    public DashboardResponseDto getCashFlow(String inititalDate, String finalDate) {
        List<TitleResponseDto> titles = titleService.findByExpirationDate(inititalDate, finalDate);
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();


        Double amountToPay = 0.0;
        Double amountToReceive = 0.0;
        double balance = 0.0;
        List<TitleResponseDto> payingTitles = new ArrayList<>();
        List<TitleResponseDto> receivingTitles = new ArrayList<>();

        for (TitleResponseDto title : titles) {

            if(title.getType() == ETitleType.PAYINGORDER){
                amountToPay += title.getAmount();
                payingTitles.add(title);
            }
            else {
                amountToReceive  += title.getAmount();
                receivingTitles.add(title);
            }
        }

        balance = amountToReceive - amountToPay;

        return new DashboardResponseDto(amountToPay, amountToReceive, balance, payingTitles, receivingTitles);

    }



}
