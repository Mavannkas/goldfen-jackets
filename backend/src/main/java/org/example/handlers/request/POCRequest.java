package org.example.handlers.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.constants.OperationTypeEnum;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class POCRequest
{
    private OperationTypeEnum operationType;
    private int number;
}
