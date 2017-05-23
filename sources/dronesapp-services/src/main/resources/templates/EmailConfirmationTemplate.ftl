<#include "Header.ftl">
		<table class="contents" style="border-collapse: collapse;border-spacing: 0;table-layout: fixed;width: 100%;">
	    	<tbody>
	    		<tr>
	    			<td>Hello ${User.username}, </td>
	    		</tr>
	    		<tr>
	    			<td>
	    				<p>
	    					Thank you for joining Drones4Hire.com. To finish activating your accont, please 
	    					<a style="color:blue;font-weight:normal" href="${verifyUrl}" target="_blank">click here</a>
	    					&nbsp;to verify your account. You can also visit the link below to verify your account.</p>
	    					<a href="${verifyUrl}" target="_blank">${verifyUrl}</a>
	    			</td>
	    		</tr>
	        </tbody>
		</table>
<#include "Footer.ftl">