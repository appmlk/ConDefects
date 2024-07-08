#ルークとポーンを互いに取れない状態で設置が可能かを判断
# ルークは斜めに設置していけば取られることはない→斜めに設置
# ポーンは隣接しなければよい
# ルークは詰めておいてもいい？→正方形を小さくするだけ？

T = int(input())

for _ in range(T):
    N,A,B = map(int, input().split())
#残りにおけるマス数
    b = N-A
    c = min((N+1)//2,b)

    print("yes" if N-A >= 0 and c * (N-A) >= B else "no")