package com.paccy.springbootne2025.services;

import com.paccy.springbootne2025.entities.PaySlip;
import com.paccy.springbootne2025.request.CreatePaySlipRequest;
import org.springframework.data.domain.Page;

public interface IPaySlipService {
    PaySlip createPaySlip(CreatePaySlipRequest createPaySlipRequest);
    Page<PaySlip> getPaySlips(int page,int size);
    Page<PaySlip> getPaySlipsInTimeRange(int page,int size,int  month,Long year);

    Page<PaySlip> approvePaySlips(int page, int size,int month,Long year);
}
