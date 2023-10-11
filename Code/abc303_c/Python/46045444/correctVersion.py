# import math
# import sys
# sys.setrecursionlimit(10**9)
# from itertools import permutations
# from itertools import combinations
# from functools import lru_cache
# import heapq

# DIV = 10**9+7

#data(yyyy-mm-dd):
#recursion使う時はCpythonで出す．それ以外は基本Pypy
def main():
    n,m,hit_point,k = map(int,input().split(" "))
    s = list(input())
    item = dict()
    for i in range(m):
        x,y = map(int,input().split(" "))
        item[(x,y)] = 1
    
    # print(item)
    flag = True
    x = 0
    y = 0
    for i in range(n):
        # print(i,x,y,hit_point,item[x][y])
        #移動経路
        char = s[i]
        if char == "R":
            x += 1
        elif char == "L":
            x -= 1
        elif char == "U":
            y += 1
        elif char == "D":
            y -= 1
        #移動する
        hit_point -= 1
        if hit_point < 0:
            print("No")
            return 1
        if hit_point < k and (x,y) in item:
            # print("recoverd")
            if item[(x,y)] > 0:
                hit_point = k
                item[(x,y)] -= 1
    print("Yes")
    return 1



if __name__ == "__main__":
    main()