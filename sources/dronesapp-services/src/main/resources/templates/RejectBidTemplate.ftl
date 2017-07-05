<#include "Header.ftl">
		<table class="contents" style="border-collapse: collapse;border-spacing: 0;table-layout: fixed;width: 100%;">
	    	<tbody>
	    		<tr>
	    			<td>Hello ${User.username}! </td>
	    		</tr>
	    		<tr>
	    			<td>
	    				<p>
	    					We are sorry, but a ${Pilot.username} canceled his bid proposal for ${Bid.amount}.
	    					Please, pick a different one (view project bids  | <a style="color:blue;font-weight:normal" href="${projectUrl}" target="_blank">${projectUrl}</a>).
	    				</p> 
	    			</td>
	    		</tr>
	        </tbody>
		</table>
<#include "Footer.ftl">