<#include "Header.ftl">
		<table class="contents" style="border-collapse: collapse;border-spacing: 0;table-layout: fixed;width: 100%;">
	    	<tbody>
	    		<tr>
	    			<td>Hello ${User.username}! </td>
	    		</tr>
	    		<tr>
	    			<td>
	    				<p>
	    					You have received a new bid proposal for your project 
	    					<a style="color:blue;font-weight:normal" href="${projectUrl}" target="_blank">${Project.title}</a>. <br />
	    					Review the bids, and pick the best.
	    			</td>
	    		</tr>
	        </tbody>
		</table>
<#include "Footer.ftl">


