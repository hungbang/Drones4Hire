<#include "Header.ftl">
		<table class="contents" style="border-collapse: collapse;border-spacing: 0;table-layout: fixed;width: 100%;">
	    	<tbody>
	    		<tr>
	    			<td>Hello ${User.username}! </td>
	    		</tr>
	    		<tr>
	    			<td>
	    				<p>
	    					Need to reset your password? No problem. Just click below to get started. <br />
	    					<a style="color:blue;font-weight:normal" href="${verifyUrl}" target="_blank">${verifyUrl}</a><br />
	    					If you didn't request to change your Drones4Hire password, please disregard this email.
	    			</td>
	    		</tr>
	        </tbody>
		</table>
<#include "Footer.ftl">