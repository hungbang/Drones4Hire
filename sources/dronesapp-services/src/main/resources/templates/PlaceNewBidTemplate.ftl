<#include "Header.ftl">
		<table class="contents" style="border-collapse: collapse;border-spacing: 0;table-layout: fixed;width: 100%;">
	    	<tbody>
	    		<tr>
	    			<td>Hello ${User.username}, </td>
	    		</tr>
	    		<tr>
	    			<td>
	    				<p>
	    					Your bid has been placed successfully for ${Project.title}. You can view this job by 
	    					<a style="color:blue;font-weight:normal" href="${projectUrl}" target="_blank">click here</a>.
	    			</td>
	    		</tr>
	        </tbody>
		</table>
<#include "Footer.ftl">