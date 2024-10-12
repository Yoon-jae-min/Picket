package com.example.picket.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class DeletePayRequestForm {

    private List<DeletePayContentForm> payTicketRequestBody;
}
