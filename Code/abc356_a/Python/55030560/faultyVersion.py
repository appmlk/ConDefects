n, l, r = map(int, input().split())

start_lis = [i for i in range(1, l)]
middle_lis = list(reversed([i for i in range(l, r+1)]))
final_lis = [i for i in range(r+1, n + 1)]

# 各リストをフラット化して1つのリストに結合
combined_lis = start_lis + middle_lis + final_lis

print(tuple(combined_lis))
