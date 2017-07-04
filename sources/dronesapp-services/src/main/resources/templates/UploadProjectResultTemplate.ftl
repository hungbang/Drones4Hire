<#include "Header.ftl">
		<table class="contents" style="border-collapse: collapse;border-spacing: 0;table-layout: fixed;width: 100%;">
	    	<tbody>
	    		<tr>
	    			<td>Hello ${User.username}! </td>
	    		</tr>
	    		<tr>
	    			<td>
	    				<p>
	    					Your pilot has uploaded new files to your project, please review the files on your project page 
	    					<a style="color:blue;font-weight:normal" href="${projectUrl}" target="_blank">${projectUrl}</a>.
	    			</td>
	    		</tr>
	        </tbody>
		</table>
<#include "Footer.ftl">