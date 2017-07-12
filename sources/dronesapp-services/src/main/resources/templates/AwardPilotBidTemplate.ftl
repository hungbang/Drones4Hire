<#include "Header.ftl">
		<table class="contents" style="border-collapse: collapse;border-spacing: 0;table-layout: fixed;width: 100%;">
	    	<tbody>
	    		<tr>
	    			<td>Hello ${User.username}! </td>
	    		</tr>
	    		<tr>
	    			<td>
	    				<p>
	    					Congratulations, You're Hired! Now accept the 
	    					<a style="color:blue;font-weight:normal" href="${projectUrl}" target="_blank">${Project.title}</a>
	    					job and fly the mission.
	    			</td>
	    		</tr>
	        </tbody>
		</table>
<#include "Footer.ftl">