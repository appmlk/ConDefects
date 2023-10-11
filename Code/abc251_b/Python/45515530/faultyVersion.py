from itertools import combinations

def ans_count(num_list, num, W, ans):
    result = 0
    combinations_list = list(combinations(num_list, num))
    for combination in combinations_list:
        # print(combination)
        if sum(combination) < W:
            ans.append(sum(combination))

    return ans


N,W = map(int,input().split())
num_map = map(int,input().split())
num_list = list(num_map)
ans = []

if len(num_list) >= 1:
    ans = ans_count(num_list,1,W,ans)
    # print(str(1) + str(ans))
if len(num_list) >= 2:
    ans = ans_count(num_list,2,W,ans)
    # print(str(2) + str(ans))
if len(num_list) >= 3:
    ans = ans_count(num_list,3,W,ans)
    # print(str(3) + str(ans))
    
num_set = set(ans)
print(len(num_set))