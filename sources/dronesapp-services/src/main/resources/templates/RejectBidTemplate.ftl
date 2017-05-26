<#include "Header.ftl">
		<table class="contents" style="border-collapse: collapse;border-spacing: 0;table-layout: fixed;width: 100%;">
	    	<tbody>
	    		<tr>
	    			<td>Hello ${User.username}, </td>
	    		</tr>
	    		<tr>
	    			<td>
	    				<p>
	    					${Project.title} has been rejected by ${Pilot.username}.  You can view this job by clicking 
	    					<a style="color:blue;font-weight:normal" href="${projectUrl}" target="_blank">click here</a> 
	    					and selecting another pilot.
	    			</td>
	    		</tr>
	        </tbody>
		</table>
<#include "Footer.ftl">