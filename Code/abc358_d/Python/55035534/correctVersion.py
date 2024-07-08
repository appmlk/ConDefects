# 入力
num_item, num_friend = input().split()
num_item, num_friend = int(num_item), int(num_friend)
item_data = sorted(list(map(int, input().split())))
friend_data = sorted(list(map(int, input().split())))
cost = 0
buy_flg = True
item_idx = 0
friend_idx=0
if item_data[-1] < friend_data[-1]:
  buy_flg=False
while friend_idx < num_friend:
  if item_data[item_idx] >= friend_data[friend_idx]:
    cost += item_data[item_idx]
    friend_idx +=1
  item_idx +=1
  if item_idx >= num_item and friend_idx < num_friend:
    buy_flg=False
    break

if buy_flg:
  print(cost)
else:
  print(-1)