<#include "Header.ftl">
		<table class="contents" style="border-collapse: collapse;border-spacing: 0;table-layout: fixed;width: 100%;">
	    	<tbody>
	    		<tr>
	    			<td>Hello ${User.username}! </td>
	    		</tr>
	    		<tr>
	    			<td>
	    				<p>
	    					Payment for project ${Project.title} received! 
	    					To transfer the sum to your credit card/paypal feel free to go to Withdrawal page( 
	    					<a style="color:blue;font-weight:normal" href="${withdrawUrl}" target="_blank">${withdrawUrl}</a>)
	    			</td>
	    		</tr>
	        </tbody>
		</table>
<#include "Footer.ftl">