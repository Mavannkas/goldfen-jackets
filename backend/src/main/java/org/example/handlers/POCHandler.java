package org.example.handlers;

import org.example.handlers.request.POCRequest;
import org.example.handlers.response.POCResponse;

import java.util.ArrayList;
import java.util.List;

public class POCHandler extends BaseHandler<POCRequest, POCResponse>
{
    private final List<Integer> numbers = new ArrayList<>();

    @Override
    protected POCResponse processRequest(POCRequest request) throws Exception
    {
        switch (request.getOperationType())
        {
            case QUERY:
                return handleQuery();
            case MUTATION:
                return handleMutation(request.getNumber());
            case SUBSCRIPTION:
                return null;
            default:
                throw new Exception("Operation type not supported");
        }
    }

    @Override
    protected void validateRequest(POCRequest request) throws Exception
    {
        if (request.getNumber() < 0)
        {
            throw new Exception("Number cannot be negative");
        }
    }

    private POCResponse handleQuery()
    {
        return new POCResponse(numbers);
    }

    private POCResponse handleMutation(int number)
    {
        this.numbers.add(number);
        return new POCResponse(List.of(number));
    }

    private void handleSubscription()
    {

    }
}
