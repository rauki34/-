<template>
	<view>
		<uni-list-chat v-for="item in Record" :title="'检测单位名称:'+item.jcdw + ' 记录编号:' + item.jlbh" avatar=""
			:note="item.jcdw+'\n'+item.jlbh+'\n'+item.gcmc+'\n'+item.sgdw" :time="item.jcrq" badge-positon="left">
			<!-- <view class="chat-custom-right">
				<text class="chat-custom-text">刚刚</text>
				<!-- 需要使用 uni-icons 请自行引入 -->
			<!-- <uni-icons type="star-filled" color="#999" size="18"></uni-icons>
			</view> -->
		</uni-list-chat>
		<uni-fab ref="fab" :pattern="pattern" :horizontal="horizontal" :vertical="vertical" :direction="direction"
			@trigger="trigger" @fabClick="fabClick" />
	</view>
</template>

<script>
	import {
		getCS42Records
	} from '@/api/system/record';
	export default {
		data() {
			return {
				title: 'uni-fab',
				horizontal: 'right',
				vertical: 'bottom',
				direction: 'horizontal',
				pattern: {
					color: '#7A7E83',
					backgroundColor: '#fff',
					selectedColor: '#007AFF',
					buttonColor: '#007AFF',
					iconColor: '#fff'
				},
				Record: [{
					id: 1,
					jcdw: '',
					jlbh: '',
					gcmc: '',
					sgdw: ''
				}]
			}
		},
		onLoad() {
			this.getRecord(); // 启动页面时候，调用函数
		},
		onPullDownRefresh() {
			console.log('在刷新数据');
			var that = this;
			var page = this.Record.length + 1;
			setTimeout(function() {
				getCS42Records(page, 6).then(res => {
					console.log(88);
					console.log(res.data);
					// res.data[0].jcdw = res.data[0].jcdw + new Date().getTime();
					that.Record = [...res.data, ...that.Record];
				})
				uni.stopPullDownRefresh();
			}, 2000);
		},
		onReachBottom() {
			//console.log("我已经到底部了");
			var that = this;
			var len = this.length;
			var page = this.Record.length / 6 + 1;
			if (len < that.total) {
				getCS42Records(page, 6).then(res => {
					console.log(88);
					console.log(res.data);
					// res.data[0].jcdw = res.data[0].jcdw + new Date().getTime();
					console.log(that.Record);
					that.Record = [...that.Record, ...res.data.list];
				})
			} else {
				console.log("我已经到底部了");
				console.log(that.Record);
			}
		},

		methods: {
			fabClick() {
				// uni.showToast({
				// 	title: '点击了悬浮按钮',
				// 	icon: 'none'
				// });
				// 跳转路由
				uni.navigateTo({
					url: "/pages/work/record/addCS42Record"
				})
			},
			// uni.request({
			// 	url: "/api/get
			// 	success: res => {
			// 		console.log(88);
			// 		console.log(res.data);
			// 		this.Record = res.data;
			// 	}
			// })
			getRecord() {
				getCS42Records(1, 20).then(res => {
					console.log(44);
					console.log(res);
					this.Record = res.data.list;
				})
			}
		}
	}
</script>

<style>

</style>