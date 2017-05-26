<#include "Header.ftl">
		<table class="contents" style="border-collapse: collapse;border-spacing: 0;table-layout: fixed;width: 100%;">
	    	<tbody>
	    		<tr>
	    			<td>Hello ${User.username}, </td>
	    		</tr>
	    		<tr>
	    			<td>
	    				<p>
	    					A new bid was just placed by ${Pilot.username} for your project ${Project.title}. You can view the project by 
	    					<a style="color:blue;font-weight:normal" href="${projectUrl}" target="_blank">click here</a>.
	    			</td>
	    		</tr>
	        </tbody>
		</table>
<#include "Footer.ftl">