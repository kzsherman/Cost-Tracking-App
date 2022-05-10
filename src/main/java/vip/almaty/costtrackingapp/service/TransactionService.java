package vip.almaty.costtrackingapp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vip.almaty.costtrackingapp.domain.Transaction;
import vip.almaty.costtrackingapp.web.TransactionRepository;

@Service
public class TransactionService
{
    @Autowired
    private TransactionRepository transactionRepo;

    public Transaction save (Transaction transaction)
    {
        return transactionRepo.save(transaction);
    }
}
