<#include "Header.ftl">
		<table class="contents" style="border-collapse: collapse;border-spacing: 0;table-layout: fixed;width: 100%;">
	    	<tbody>
	    		<tr>
	    			<td>Hello ${User.username}, </td>
	    		</tr>
	    		<tr>
	    			<td>
	    				<p>
	    					Congratulations! ${Project.title} has been awarded to you! Please login and accept the job on your behalf. 
	    					This shows that you have acknowledge and accepted the client's job. You can view this job by clicking
	    					<a style="color:blue;font-weight:normal" href="${projectUrl}" target="_blank">click here</a>.
	    			</td>
	    		</tr>
	        </tbody>
		</table>
<#include "Footer.ftl">