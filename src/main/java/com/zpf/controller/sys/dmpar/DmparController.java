
package com.zpf.controller.sys.dmpar;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @ClassName: DmparController
 * @Description: 数据字典相关controller
 * @author: zhangpengfei E-mail:pengfei19890227@163.com
 * @date: 2017年8月26日 下午11:12:19
 */
@Controller
@RequestMapping("/dmparController")
public class DmparController {

	@RequestMapping("/gotoDmpar")
	public String gotoDmpar(ModelMap modelMap) {
		return "/sys/dmpar/dmpar";
	}

}
