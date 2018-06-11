
/**
 * 将timestamp 转为 字符串
 * 使用方式：var time=new Date(timestamp).format("dd/MM/yyyy hh:mm:ss");
 * @param fmt
 * @returns
 */
Date.prototype.Format = function (fmt) { 
        var o = {
            "M+": this.getMonth() + 1, //Month
            "d+": this.getDate(), //Day
            "h+": this.getHours(), //Hour
            "m+": this.getMinutes(), //Minute
            "s+": this.getSeconds(), //Second
            "q+": Math.floor((this.getMonth() + 3) / 3), //Season
            "S": this.getMilliseconds() //millesecond
        };
        if (/(y+)/.test(fmt)) 
        	fmt = fmt.replace(RegExp.$1, (this.getFullYear() + "").substr(4 - RegExp.$1.length));
        
        for (var k in o)
            if (new RegExp("(" + k + ")").test(fmt))
            	fmt = fmt.replace(RegExp.$1, (RegExp.$1.length == 1) ? (o[k]) : (("00" + o[k]).substr(("" + o[k]).length)));
                return fmt;
};



$.extend({
	StandardPost:function(url,args){
		var form = $("<form method='post' target='_blank'></form>"), input;         
		form.attr({"action":url});         
		$.each(args,function(key,value){            
			input = $("<input type='hidden'>");             
			input.attr({"name":key});            
			input.val(value);             
			form.append(input);         
		});
		form.appendTo(document.body);
		form.submit();
		document.body.removeChild(form[0]);
	} 
});