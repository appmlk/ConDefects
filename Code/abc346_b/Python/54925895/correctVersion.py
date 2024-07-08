white,black = map(int,input().split())
sum = white + black
part_list = []



str_S = 'wbwbwwbwbwbw' #12
S_list = list(str_S)
S_list_sum = 12

while sum > S_list_sum:
    S_list.extend(str_S)
    S_list_sum += 12
    
S_list.extend(str_S)
S_list_sum += 12

for x in range(S_list_sum):
    part_list = S_list[x:x+sum]
    white_count = part_list.count('w')
    black_count = part_list.count('b')
    
    if white_count == white and black_count == black:
        print('Yes')
        exit()

print('No')