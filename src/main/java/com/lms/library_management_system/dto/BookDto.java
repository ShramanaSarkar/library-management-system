package com.lms.library_management_system.dto;

import java.time.LocalDateTime;

import com.lms.library_management_system.entity.User;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BookDto {

	private int bookId;

	private String title;

	private String author;

	private LocalDateTime borrowedTime;

	private LocalDateTime returnTime;

	private User user;
}
