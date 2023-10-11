# https://atcoder.jp/contests/abc258/tasks/abc258_a

K = int(input())

if K > 69:
    print(f"22:{K - 60}")
elif K > 59:
    print(f"22:0{K - 60}")
elif K < 10:
    print(f"21:0{K}")
else:
    print(f"21:{K}")