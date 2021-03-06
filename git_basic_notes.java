3.

git log
	git log -n 1 // git log -n [how many commits that it returns to you]
	git log --since=2012-06-12
	git log --until=2012-06-12
	git log --author="Kevin"
	git log --grep="Init"
	git log 




4.

three-trees architecture
git log HEAD // where is the current HEAD, the current branch

5.

git diff // compare the difference between "the HEAD pointing to" and "the current we are working with"
		 // ---   the version in the repository, previous commited one
		 // +++   new version

git diff file.txt

git diff --staged // compare staging index and the repository

delete:
	1. drap the file_to_delete.txt to trash
	2. git rm file_to_delete.txt // delete the file that already been committed.
	3. commit

rename:
	git mv current_file_name.txt changed_file_name.txt
	


6. 

toggle fold long lines: minus sign(-) + shift + s + return

git diff --color-words file.txt

git commit -am "commit message" // add and commit together

7.

Undoing working directory changes:


	git checkout // go to the repository, get the named thing that I've gave you, and make my working directory look like that. 更改过文件后，恢复成当前repository中的版本

	git checkout -- index.html // -- 就是check a file in the current branch

Unstaging files: (已经git add的文件，返回到没有add的时候，unstage changes)
	git reset HEAD file.txt // go look at the HEAD pointer. The HEAD pointer points to the last commit of the tip of the current branch, which is master.

Amending commits: (change last version's commit messages)
	git commit --amend -m "xxxx"

Retriveing old version with SHA
	1. git checkout 2907d126 -- file.txt 
	2. git diff --staged // 可以比较staged的version（2907d126 version）和现在的文件的不同，如果commit则变成2907d126 version

Reverting a commit
	git revert [SHA]

git reset
	1. --soft: 仅仅是move HEAD to a version，但是不做任何其他的改变，经常用于比较diff

	git reset --soft [SHA]
		之后cat .git/refs/heads/master会有变化，变成[SHA]
		可以：1. 从此之后，覆盖原先的所有变化，commit based on current version
			 
			 2. 或者比较diff，然后返回之后的version
			 git diff --staged
			 git reset --soft [previous SHA]

	2. --mixed(default): staging index也变成那个version的，但是current repository不做变化，只是现在的内容. wait to be commited
		// makes the staging index look the same as what's in the repository




	3. --hard：所有东西of later version are gone

Removing untracked files(永远的删除，trash里面也没有)
// in current repository，staging本身以及staging之后的都不管	

	git clean -n/-f // -n: test run
					// -f: force run


// 如果想把staging上面的也clean
	1. git reset HEAD file.txt
	2. git clean -f

8.

.gitignore (everything that ignore the changes)

	# Comment
	.DS_Store
	*.zip
	*.gz
	log/*.log
	log/*.log.[0-9] # anything 0~9 at the end
	assets/photoshop/
	assets/videos/
	!assets/videos/tour_*.mp4

	# log/archive/access.log will not be ignored.*/

Ignoring files globally: can keep some files locally
	git config --global core.excludesfile ~/.gitignore_global

Ignoring tracked files
	ignore的话（加入.gitignore），在add commit push之前有用，之后就没有用，还会继续track

	tell git not to track these files:
		git rm --cached file.txt // remove this file from the staging index, not from the repository, just from the staging index.
								// stop being tracked, but will leave the copyt int he repo.

Tracking empty directories: git tracks files not directories.
	在系统里面，touch assets/pdfs/,gitkeep (pdfs是个空文件夹)


9.

Tree-ish - the structure of files in the Git repository

git ls-tree [Treeish]

git ls-tree HEAD       <=>
git ls-tree master

git ls-tree master assets/ // tells the files that are inside that directory

git ls-tree master^ assets/  // back one commit

blob is a file.
tree is a directory


git log --online // 把所有log变成一行显示出来
git log --online -3 // show 3 logs
git log --since="2012-06-12"
git log --until="xxxx-xx-xx"
git log --since="2 weeks ago" --until="3 days ago"
git log --since=2.weeks --until=3.days
git log author="Kevin"
git log --grep="temp"
git log [SHA-1]..[SHA-2] --oneline
git log [SHA-1].. index.html // the way up to the end, what has happened to the index.html file
git log -p [SHA-1].. index.html // -p show the changes
git log --stat --summary // tell you statistics about what changed in each one. Something was added, something was taken away.
git log --format=oneline // 比之前oneline多加了SHA
git log --format=short 
git log --format=full
git log --format=fuller 
git log --format=email
git log --format=raw 
git log --graph
git log --online --graph --all --decorate

Viewing commits in a specific log, what changes in this commit:
	git show [SHA]
	git show --formate=oneline HEAD
	git show --formate=oneline HEAD^
	git show --formate=oneline HEAD^^
	git show --formate=oneline HEAD~3


Comparing commits: compare the directory that the commit references; the actural state of all the files in the repository at that point
	git diff [SHA] // diff between that directory and current directory
	git diff [SHA] file.txt
	git diff [SHA1]..[SHA2]
	git diff [SHA1]..[SHA2] file.txt
	git diff --stat --summary [SHA]..HEAD
	git diff --ignore-space-change [SHA]..HEAD // ignore one/two space change
	// git diff -b [SHA]..HEAD
	git diff --ignore-all-space-change [SHA]..HEAD
	// git diff -w [SHA]..HEAD



10. Branching
 	git branch // the branch we have, 有*号标志的说明先在在的branch
 	ls -la .git/refs/heads // all the branching
 	create: 
 		git branch new_feature // add new branch

 	switch:	
 		git checkout new_feature // switch to branch
 	create + switch:
 		git checkout -b shorthen_title
 	

 	checkout command: tells Git to go and get the latest version of the branch and to make our working directory look exactly like it. checkout的时候，directory必须是most clean的，如果不most clean，依旧有changes没有被commit，是不能switch branch的。解决方案：1. 去除改动 2. commit 3. stash。如果有新加上的files，也是可以switch的（most clean）


 	compare:
 		git diff master..new_feature //比较master和new_feature之间的不同
        git diff --color-words new_feature..master // 把不同显示在一行里
        git diff --color-words new_feature..master^ // 比较new_feature和master上一次commit的不同
                                                    // 用这种方式来看两个branch什么时候完全相同, completely contains one another
        git branch --merged // show all branches that are completely included in this branch, 当前branch里面所有的branch的内容
            example: // 现在的branch是shorthen_title, all of the commits that are in new_feature and master are also in shorten_title
                master
                new_feature
              * shorthen_title


    rename:
        git branch -m new_feature seo_title // 把原来名字（new_feature）改成seo_title
    delete:
        git branch -d branch_to_delete // 注：cannot delete the branch you're currently on
                                        //  如果被delete的branch有没被merge的内容，会有提醒，问是否completely delete
    (设置)每次回车的时候，告诉你现在在哪个branch:
command: ls -la ~ //看有没有.git-completion.bash


11.

    git checkout master // master接受merge
    git merge seo_title

    git merge --no-ff branch // dont do fast forware, make a log
    git merge --ff-only branch // 只做fast forware merge，如果不行，则不merge

Resolve merge conflicts:
    1. abort merge: git merge --abort
    2. resolve the conflicts manually: 手动改错，代码里面会有>>>HEAD 之类的
    3. use a merge tool: git mergetool --tool=

    git log --graph --oneline -all --decorate // 看图

12.

stash可以暂时存changes，并且不用commit。The stash is not part of the repository, the staging index or the working directory, it's a special fourth area in Git.

在A branch改过文件之后，没有add commit，想switch branch。用stash。
    git stash save "messages"

    git stash list // 看stash里面所有的东西
    stash@{0}: On [branch that put the stash on]: messages

    git stash show stash{0} // what change int the file
    git stash show stash{0} // show us the edits


Retrive the stash：在哪个branch retrieve并不重要，每个都能apply stash内的内容
    git stash pop（或者apply）stash{0}// pop 同时remove stash内的内容
                            // apply只拿出来，不remove
    git stash save "messages" // save the poped stash again, 如果不想在当前branch retrieve stash
    git stash

Delete items in the stash
    git stash drop stash{0}
    git stash clear // clear all stash

13.
origin/master // a branch on our local machine that references the remote server branch, and it always tries to stay and sync with that.

remote server     master

my computer       origin/master
                  master

push: master(my computer) -> master(remote server)
fetch: master(remote server) -> origin/master(my computer)
merge: origin/master(my computer) -> master(my computer)


    git remote // show all the remote
    git remote -v // 更详细的remote信息
    cat .git/config // 查看origin的信息，configuration

    git remote add origin <https://github......>

    ls -la .git/refs/remotes // 可以显示remotes的名字
    ls -la .git/refs/remotes/origin // 可以显示origin里面的branches, e.g. master之类的

push:
    git push -u origin master // -u 说明连接到remote
// git branch --set-upstream non_tracking origin/non_tracking 设置-u

    git branch -r // remote branch
    git branch -a // show local branch and remote branch

    cat .git/config里面的[branch "master"] remote = origin, [remote "origin"]里面的url是github的url
clone:
    git clone [path] [optional: 如果当前已经有了该文件夹，就会需要写这个，可以标记为另一个version，别人已经更改过的version，在github上]

push changes to a remote repository:
    git diff origin/master..master
    git push // 因为是tracking branch，就是完全从git上面clone下来的，所以可以直接git push

fetch
    git fetch // 或者是git fetch origin

merge
    git branch -a // 所有的branch
    git diff master..origin/master // 比较想merge的两个branch的不同
    git merge origin/master // 因为现在在的branch是master

    git pull = git fetch + git merge

check out remote branches
    git branch -r // 看remote的branches
    git branch non_tracking origin/non_tracking // 把remote的non_tracking branch变成本地的non_tracking branch了
    git branch -d non_tracking // 删除本地的（不是remote）non_tracking branch
    git checkout -b non_tracking origin/non_tracking // 这样commit之后push会push到remote non_tracking

delete remote branch
    git branch -a // 先show所有的branches，包括remote的
    方法1：git push origin :non_tracking // 实际上push是non_tracking:non_tracking把non_tracking local push到non_tracking remote
    // 现在变成:non_tracking，也就是把nothing push到non_tracking

    方法2：git push origin --delete non_tracking

Collaboration:
    1. 被加成collaborator之后，看看network、pull request、issues
    2. 之后fork到自己的github和local
    3. commit changes to 自己的github
    4. pull request，合并到原来的github中

A collaboration workflow
    > git checkout master // 去master branch
    > get fetch // 每天都fetch别人的成果
    > git merge origin/master // synchornize the origin/master with master
// 在新的branch里面开始work，这样不会affect master branch
    > git checkout -b feedback_form // create and 换成feedback_form branch
    > git add feedback.html
    > git commit -m "Add customer feedback form" // 现在changes在local repository inside feedback_form branch
// 打算push到remote上面了，先fetch别人的成果
    > git fetch
    > git push -u origin feedback_form // -u说明是tracking branch，这样以后也能track changes

另一个coworker：
    > git checkout master
    > git fetch
    > git merge origin/master // 把fetch的成果merge到local
// 看feed_back的work
    > git checkout -b feedback_form origin/feedback_form // 看origin/feedback_form里面的改动
    > git log
    > git show [SHA] // look at actual commit
// make changes
    > git commit -am "Add tour selector to feedback form"
// 放在remote
    > git fetch
    > git push

我：
    > git fetch
    > git log -p feedback_form..origin/feedback_form // -p will show each of the log entries with a diff of all the changes that were made in that log entry
    > git merge origin/feedback_form
    > git checkout master
    > git fetch
    > git merge origin/master
    > git merge feedback_form
    > git push













