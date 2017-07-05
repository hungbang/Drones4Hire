<#include "Header.ftl">
		<table class="contents" style="border-collapse: collapse;border-spacing: 0;table-layout: fixed;width: 100%;">
	    	<tbody>
	    		<tr>
	    			<td>Hello ${User.username}! </td>
	    		</tr>
	    		<tr>
	    			<td>
	    				<p>
	    					We take into account your account preferences and have prepared a list of new projects for you: <br />
	    					<a style="color:blue;font-weight:normal" href="${projectUrl}" target="_blank">${Project.title}</a> - Bid ${Project.budget.min} - ${Project.budget.max} ${Project.budget.currency} <br />
	    					Post your bid, until someone else did this!
	    				</p> 
	    			</td>
	    		</tr>
	        </tbody>
		</table>
<#include "Footer.ftl">