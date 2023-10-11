# ||￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣||
#　　　　　　　　早速解いていくよ
#
# 　||　　　　　　　　　　　　　　　　　　　　　　 ＼　(ﾟーﾟ*)　ｷﾎﾝ。
# 　||＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿ 　⊂⊂ |
# 　　　　∧ ∧　　　 ∧ ∧　　　 ∧ ∧　　　 |￣￣￣￣|
# 　　　 (　　∧ ∧　(　　 ∧ ∧　(　　∧ ∧　|　　　　　　|
# 　　～（＿(　　∧ ∧ __(　　∧ ∧__(　　 ∧ ∧￣￣￣
# 　　　　～（＿(　　∧ ∧＿(　　∧ ∧＿(　　 ∧ ∧ 　は～い、先生。
# 　　　　　　～（＿(　 　,,)～（＿(　 　,,)～（＿(　 　,,)
# 　　　　　　　　～（＿__ﾉ　　～（＿__ﾉ 　　～（＿__ﾉ
# -*- coding: utf-8 -*-
#
import sys
def input():
    return sys.stdin.readline().rstrip()
MOD=998244353
W,H,K=map(int,input().split())
dp=[[0,0,0,0]]
x1,y1,x2,y2=map(int,input().split())
if x1!=x2:
    if y1!=y2:
        dp[0][0]=1
    else:
        dp[0][1]=1
else:
    if y1!=y2:
        dp[0][2]=1
    else:
        dp[0][3]=1
for k in range(K):
    bucket=[0,0,0,0]
    bucket[0]+=(H-2+W-2)*dp[-1][0]+(H-1)*dp[-1][1]+(W-1)*dp[-1][2]+0
    bucket[1]+=dp[-1][0]+(W-2)*dp[-1][1]+(W-1)*dp[-1][3]
    bucket[2]+=dp[-1][0]+0+(H-2)*dp[-1][2]+(H-1)*dp[-1][3]
    bucket[3]+=0+dp[-1][1]+dp[-1][2]+0
    dp.append([b%MOD for b in bucket])
print(dp[-1][-1])
