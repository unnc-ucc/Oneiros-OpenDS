# Oneiros-OpenDS
## Introduction
Oneiros-OpenDS is an interactive and extensible toolkit for agile and automated developments of complicated driving scenes.
Full paper is accepted by HCII'22 and the preprint version is accessible at [here](https://www.researchgate.net/publication/358532511_Oneiros-OpenDS_An_Interactive_and_Extensible_Toolkit_for_Agile_and_Automated_Developments_of_Complicated_Driving_Scenes?_sg%5B0%5D=jciXj7UMWwmQv6H2sHBDxYGwuLdFPStFizK5yxhoDvzoO25UCzW4DnfchaMZK_1VulvMYK4guJ71lgDJrOi7CKJ40bIMOEOPRCdsMgyL.Bk32OxJ0H-Hol4C3kaM6ExA9Y-5OcdXi7q6rlC1FaWRcsqOaUny_zQMu9Hssn_znkx5nrWuou7Zun7DcB7P0kg).
## Motivation
Motivated by the inefficiency for the developments of complicated driving scenes, Oneiros aims to:

(1) Provide a toolkit for more efficient and agile developments of complicated driving scenes

(2) Ensure designers can independently undertake most implementations of driving scenes via a to()olkit

Therefore: we derive two key requirements of such a toolkit: 

(1) Preview Functions are very important to allow designers for introspection and rectifications of their designs.

(2) Graphical User Interfaces are also very important to ease designers for creation, adjustments and adaptions of different objects and factors.

## Design
Oneiros enables a single designer to design, implement and adjust his/her own designs of driving scenes.


## Implementation Details
Oneiros is implemented in JAVA (the same language as OpenDS). It use MVC design pattern and provide preview and code generation functions.

There are essentially four steps: 

(1) the user draws the designs of driving scenes through the GUI of Oneiros

(2) the user generates source codes of the driving scene, through the code generator of Oneiros

(3) the user previews the design through visualization supports of Oneiros

(4) the user can summarize key issues of existing designs. If the design still needs improvements, the user can directly rectify the design by repeating the first three steps. Otherwise, the user can export the source code

![d](./resource/new-figure2a.png)


## User Study
We recruit 11 participants by advertising our study via internal emails, among all students (i.e. 36) involving driving-scene designs previously in our lab. The result is as follows:

**Efficiency Improvements - in terms of Time**
![1](./resource/new-result2d.png)
First, there are 9 participants who save at least 32% time by using Oneiros, compared with the conventional approach. Second, 1 participant saves up to 69% time by using Oneiros. Third, 2 participants only save 5% and 11% time by Oneiros. This is because these two participants are cold-start in using Oneiros, without sufficient practices.

**User Experiences - in terms of Functionality, Interactivity and Convenience**
![2](./resource/new-result2e.png)
For Functionality, 1 participant thinks the functionality of Oneiros tool is good enough, 7 participants think is good, and 3 participants think the functionality is neutral; for Interactivity, all participants consider Oneiros is much more interactive than the conventional approach: 9 of them particularly enjoy the interactivity of Oneiros; for Convenience, 4 participants think using Oneiros is much more convenient than the conventional approach, and 7 participants think Oneiros is more convenient.


**Code Contributor**

@[Shuolei Wang](https://github.com/ShuoleiWang), @[Junyu Liu](https://github.com/Junyu-Liu-Nate), mentored by @[Xiangjun Peng](https://github.com/Shiangjun).
