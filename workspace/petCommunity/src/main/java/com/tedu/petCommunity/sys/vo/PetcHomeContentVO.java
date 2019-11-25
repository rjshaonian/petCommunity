package com.tedu.petCommunity.sys.vo;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author 阳昊 2019年11月26日 上午2:40:24
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PetcHomeContentVO<T> {
	private String url;
	private List<T> list;
}
