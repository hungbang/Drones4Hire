<#include "Header.ftl">
		<table class="contents" style="border-collapse: collapse;border-spacing: 0;table-layout: fixed;width: 100%;">
	    	<tbody>
	    		<tr>
	    			<td>Hello ${User.username}! </td>
	    		</tr>
	    		<tr>
	    			<td>
	    				<p>
	    					A new comment has been posted for your project 
	    					<a style="color:blue;font-weight:normal" href="${projectUrl}" target="_blank">${Project.title}</a>. <br />
	    					Please let the pilots know details about your project, so they can send you a better bid proposal.
	    			</td>
	    		</tr>
	        </tbody>
		</table>
<#include "Footer.ftl">