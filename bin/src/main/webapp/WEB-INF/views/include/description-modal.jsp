<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	
<style>
	#descriptionModal .modal-dialog {
		width: fit-content;
		margin-top: 10%;
	}
	
	.card img {
		width: 100%;
		border-radius: 12px;
	  	height: 214px;
		object-fit: cover;
	}
	
	.card {
		/* Change background color */
		background-color: white;
		/* Add border */
		border: 1px solid #bacdd8;
		/* Add space between the border and the content */
		padding: 8px;
		border-radius: 12px;
	}
	
	.tag {
		padding: 4px 8px;
		border: 1px solid #e5eaed;
		border-radius: 50px;
		font-size: 12px;
		font-weight: 600;
		color: #788697;
	}
	
	.name {
		font-size: 24px;
		font-weight: 600;
		margin-top: 16px;
	}
	
	p {
		font-size: 14px;
		color: #7f8c9b;
		line-height: 150%;
	}
</style>

<div class="modal fade" id="descriptionModal">
	<div class="modal-dialog">
		<div class="modal-content">
			<h1>여기에 내용</h1>
		</div>
	</div>
</div>

