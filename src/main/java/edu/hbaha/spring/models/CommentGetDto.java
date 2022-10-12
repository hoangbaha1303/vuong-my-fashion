package edu.hbaha.spring.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CommentGetDto {
	private Integer productID;
	private Double avgStar;
	private int countComment;
}
