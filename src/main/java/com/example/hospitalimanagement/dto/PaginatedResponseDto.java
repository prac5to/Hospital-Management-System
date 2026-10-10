package com.example.hospitalimanagement.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
//this paginated response dto actually reacts with frontend with response of total no of pages,total patients,location last page 
public class PaginatedResponseDto<T>
//<T> ye har ek dto ka paginated response dega ok.
{
 private List<T> content; //full data list
 private int pageNumber;  //current index front end me define
 private int pageSize;   //at a time how many items front end me
 private long totalElements; //total records
 private int totalPages;     
 private boolean last;      //last page counter ,boolean
}
