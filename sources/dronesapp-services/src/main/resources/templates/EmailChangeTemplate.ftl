<#include "Header.ftl">
		<table class="contents" style="border-collapse: collapse;border-spacing: 0;table-layout: fixed;width: 100%;">
	    	<tbody>
	    		<tr>
	    			<td>Hello ${User.username}, </td>
	    		</tr>
	    		<tr>
	    			<td>
	    				<p>
	    					Please <a style="color:blue;font-weight:normal" href="${verifyUrl}" target="_blank">click here</a>
	    					&nbsp;to verify your new email.
	    				</p>
	    			</td>
	    		</tr>
	        </tbody>
		</table>
<#include "Footer.ftl">