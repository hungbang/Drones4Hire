<#include "Header.ftl">
		<table class="contents" style="border-collapse: collapse;border-spacing: 0;table-layout: fixed;width: 100%;">
	    	<tbody>
	    		<tr>
	    			<td>Hello ${User.username}! </td>
	    		</tr>
	    		<tr>
	    			<td>
	    				<p>
	    					Your account hasn’t been verified. Please 
	    					<a style="color:blue;font-weight:normal" href="${verifyUrl}" target="_blank">verify</a>
	    					&nbsp;your account to gain access.
	    			</td>
	    		</tr>
	        </tbody>
		</table>
<#include "Footer.ftl">