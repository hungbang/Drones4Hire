<#include "Header.ftl">
		<table class="contents" style="border-collapse: collapse;border-spacing: 0;table-layout: fixed;width: 100%;">
	    	<tbody>
	    		<tr>
	    			<td>Hello ${User.username}! </td>
	    		</tr>
	    		<tr>
	    			<td>
	    				<p>
	    					Pilot ${Pilot.username} can start the job, please use a project discussion at the  
							<a style="color:blue;font-weight:normal" href="${projectUrl}" target="_blank">project page</a> 
							&nbsp;to agree on details of the work.
	    				</p>
	    			</td>
	    		</tr>
	        </tbody>
		</table>
<#include "Footer.ftl">