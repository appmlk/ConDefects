def ip():return int(input())
def mp():return map(int, input().split())
def lmp():return list(map(int, input().split()))
# ABC251 D 1463 - At Most 3 (Contestant ver.)
# 整数 W が与えられます。
# あなたは以下の条件をすべて満たすようにいくつかのおもりを用意することにしました。
# ・おもりの個数は 1 個以上 300 個以下である。
# ・おもりの重さは 10^6 以下の正整数である。
# ・1 以上 W 以下のすべての正整数は 良い整数 である。ここで、以下の条件を満たす正整数 n を良い整数と呼ぶ。
#   用意したおもりのうち 3 個以下 の異なるおもりを自由に選んで、選んだおもりの重さの和を n にすることができる。 　
# 条件を満たすようなおもりの組を 1 つ出力してください。
# ・1 ≤ W ≤ 10^6
W = ip()
A = []
for i in range(1, 101):
    A.append(i)
    A.append(100*i)
    A.append(10000*i)
print(300)
print(*A)